SUMMARY = "Platform support library used by libCEC and binary add-ons for Kodi"
HOMEPAGE = "http://libcec.pulse-eight.com/"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://src/os.h;md5=752555fa94e82005d45fd201fee5bd33"

PV = "2.0.1"

SRCREV = "38343e0acd6a636ac46139aa666aee4a8d1f13db"
SRC_URI = "git://github.com/Pulse-Eight/platform.git"

S = "${WORKDIR}/git"

inherit cmake

FILES_${PN}-dev += "${libdir}/p8-platform"

