SUMMARY = "Kodi Media Center"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE.GPL;md5=930e2a5f63425d8dd72dbd7391c43c46"

DEPENDS = "ffmpeg libsquish libusb1 dcadec libcec libplist expat yajl gperf-native libxmu fribidi mpeg2dec samba fontconfig curl python libass libmodplug libmicrohttpd wavpack libmms cmake-native libsdl-image libsdl-mixer virtual/egl mysql5 sqlite3 libmms faad2 libcdio libpcre boost lzo enca avahi libsamplerate0 libxinerama libxrandr libxtst bzip2 virtual/libsdl jasper zip-native zlib libtinyxml libmad libxslt taglib libssh nasm-native yasm-native"
#require recipes/egl/egl.inc


SRCREV = "b587e98911fcedc4b800cd923a882c7aeeaa2146"

PV = "16.0+gitr${SRCPV}"
SRC_URI = "git://github.com/xbmc/xbmc.git;branch=Jarvis \
           file://0001-configure-don-t-try-to-run-stuff-to-find-tinyxml.patch \
           file://0002-arm64-Fix-build-breakages-due-to-architecture-specif.patch \
"

inherit autotools-brokensep gettext python-dir

S = "${WORKDIR}/git"

# breaks compilation
CCACHE = ""
ASNEEDED = ""

CACHED_CONFIGUREVARS += " \
    ac_cv_path_PYTHON="${STAGING_BINDIR_NATIVE}/python-native/python" \
"

PACKAGECONFIG ??= "vaapi ${@base_contains('DISTRO_FEATURES', 'opengl', 'opengl', 'openglesv2', d)}"
PACKAGECONFIG[opengl] = "--enable-gl,--enable-gles,glew"
PACKAGECONFIG[openglesv2] = "--enable-gles,--enable-gl,"
PACKAGECONFIG[vaapi] = "--enable-vaapi,--disable-vaapi,libva"
PACKAGECONFIG[vdpau] = "--enable-vdpau,--disable-vdpau,libvdpau"

EXTRA_OECONF = " \
    --enable-libusb \
    --enable-airplay \
    --disable-optical-drive \
    --with-ffmpeg=shared \
    ${@base_contains('DISTRO_FEATURES', 'opengl', '--enable-gl', '--enable-gles', d)} \
"

FULL_OPTIMIZATION_armv7a = "-fexpensive-optimizations -fomit-frame-pointer -O4 -ffast-math"
BUILD_OPTIMIZATION = "${FULL_OPTIMIZATION}"

EXTRA_OECONF_append_armv7a = "--cpu=cortex-a8"

# for python modules
export HOST_SYS
export BUILD_SYS
export STAGING_LIBDIR
export STAGING_INCDIR
export PYTHON_DIR

do_configure() {
    make -C tools/depends/target/crossguid PREFIX=${STAGING_DIR_HOST}${prefix}
    sh bootstrap
    oe_runconf
}

do_compile_prepend() {
    for i in $(find . -name "Makefile") ; do
        sed -i -e 's:I/usr/include:I${STAGING_INCDIR}:g' $i
    done

    for i in $(find . -name "*.mak*" -o    -name "Makefile") ; do
        sed -i -e 's:I/usr/include:I${STAGING_INCDIR}:g' -e 's:-rpath \$(libdir):-rpath ${libdir}:g' $i
    done
}

INSANE_SKIP_${PN} = "rpaths"

FILES_${PN} += "${datadir}/xsessions ${datadir}/iconsi ${libdir}/xbmc ${datadir}/xbmc"
FILES_${PN}-dbg += "${libdir}/kodi/.debug ${libdir}/kodi/*/.debug ${libdir}/kodi/*/*/.debug ${libdir}/kodi/*/*/*/.debug"

# kodi uses some kind of dlopen() method for libcec so we need to add it manually
# OpenGL builds need glxinfo, that's in mesa-demos
RRECOMMENDS_${PN}_append = " libcec \
                             python \
                             python-lang \
                             python-re \
                             python-netclient \
                             libcurl \
                             xdpyinfo \
                             ${@base_contains('DISTRO_FEATURES', 'opengl', 'mesa-demos', '', d)} \
"
RRECOMMENDS_${PN}_append_libc-glibc = " glibc-charmap-ibm850 \
                                        glibc-gconv-ibm850 \
                                        glibc-gconv-utf-32 \
                                      "
