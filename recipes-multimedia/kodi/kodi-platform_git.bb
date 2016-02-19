SUMMARY = "Platform support library used by libCEC and binary add-ons for Kodi"
HOMEPAGE = "http://libcec.pulse-eight.com/"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://src/util/XMLUtils.cpp;beginline=2;endline=18;md5=dae8e846500e70dd8ecee55f3f018c30"

DEPENDS = "libtinyxml kodi"

PV = "16.0.0"

SRCREV = "15edaf78d6307eaa5e1d17028122d8bce9d55aa2"
SRC_URI = "git://github.com/xbmc/kodi-platform.git \
           file://0001-Fix-build-after-platform-rename.patch \
           file://0001-fix-cross-compile-badness.patch \
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

FILES_${PN}-dev += "${libdir}/*platform"

