SUMMARY = "Kodi Media Center PVR plugins"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://src/client.cpp;beginline=1;endline=19;md5=101ceb1255392ba347350e6c3e543f3a"

DEPENDS = " \
            zip-native \
            p8platform \
            kodi-platform \
          "

SRCREV_pvrhts = "8929c220a69f6349c4ab72d349f391901c3e59eb"

SRCREV_FORMAT = "pvrhts"

PV = "3.3.1+gitr${SRCPV}"
SRC_URI = "git://github.com/kodi-pvr/pvr.hts.git;branch=master;destsuffix=pvr.hts;name=pvrhts \
          "

inherit cmake pkgconfig gettext

S = "${WORKDIR}/pvr.hts"

EXTRA_OECMAKE = "\
	  -DADDONS_TO_BUILD=pvr.hts \
	  -DADDON_SRC_PREFIX=${WORKDIR}/git \
	  -DCMAKE_BUILD_TYPE=Debug \
	  -DCMAKE_INSTALL_PREFIX=${datadir}/kodi/addons \
          -DCMAKE_MODULE_PATH=${STAGING_LIBDIR}/kodi \
	  -DCMAKE_PREFIX_PATH=${prefix} \
          -DPACKAGE_ZIP=1 \
        "

do_compile_prepend() {
	sed -i -e 's:I/usr/include:I${STAGING_INCDIR}:g' \
	       -e 's:-pipe:${HOST_CC_ARCH} ${TOOLCHAIN_OPTIONS} -pipe:g' \
	          ${B}/CMakeFiles/*/flags.make
	sed -i -e 's:-pipe:${HOST_CC_ARCH} ${TOOLCHAIN_OPTIONS} -pipe:g'\
	          ${B}/CMakeFiles/*/link.txt
}

# Make zip package for manual installation
do_install_append() {
	install -d ${D}${datadir}/kodi/addons/packages/
	( cd ${D}${datadir}/kodi/addons
	  zip -r ${D}${datadir}/kodi/addons/packages/pvr.hts-${PV}.zip pvr.hts -x '*.debug*' )
}

# Doesn't get added automagically, dlopen()?
RDEPENDS_${PN} = "libkodiplatform"

INSANE_SKIP_${PN} = "dev-so"
FILES_${PN} += "${datadir}/kodi"
FILES_${PN}-dbg += "${datadir}/kodi/addons/*/.debug/"
