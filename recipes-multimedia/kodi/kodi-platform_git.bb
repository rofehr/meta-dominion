SUMMARY = "Platform support library used by libCEC and binary add-ons for Kodi"
HOMEPAGE = "http://libcec.pulse-eight.com/"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://src/util/XMLUtils.cpp;beginline=2;endline=18;md5=dae8e846500e70dd8ecee55f3f018c30"

DEPENDS = "libtinyxml kodi"

PV = "17.0.0"

SRCREV = "92583ef9f87bfcea1f2b4fc1310fdc673a2fe924"
SRC_URI = "git://github.com/xbmc/kodi-platform.git \
           file://kodi-platform-02_no-multi-lib.patch \
          "

S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE = " -DCMAKE_INSTALL_PREFIX_TOOLCHAIN=${STAGING_DIR_TARGET} "

do_compile_prepend() {
	sed -i -e 's:I/usr/include:I${STAGING_INCDIR}:g' \
	       -e 's:-pipe:${HOST_CC_ARCH} ${TOOLCHAIN_OPTIONS} -pipe:g' \
	          ${B}/CMakeFiles/kodiplatform.dir/flags.make
	sed -i -e 's:-pipe:${HOST_CC_ARCH} ${TOOLCHAIN_OPTIONS} -pipe:g'\
	          ${B}/CMakeFiles/kodiplatform.dir/link.txt
}

do_install_append() {
	install -d ${D}${libdir}
 	mv ${D}/pkgconfig ${D}${libdir}
	mv ${D}/kodiplatform ${D}${libdir}
}

RPROVIDES_${PN} += "libkodiplatform"
PACKAGES =+ "libkodiplatform"

FILES_libkodiplatform = "${libdir}/lib*.so.*"

FILES_${PN}-dev += "${libdir}/*platform"

