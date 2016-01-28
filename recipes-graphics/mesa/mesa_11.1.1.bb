require recipes-graphics/mesa/${BPN}.inc

SRCREV = "ee5d530d338b451cdc37fdaf0a54ec958fcf605e"
SRC_URI = "git://anongit.freedesktop.org/git/mesa/mesa.git;protocol=http;branch=11.1"

S = "${WORKDIR}/git"

DEPENDS += "python-mako-native"

inherit pythonnative

#because we cannot rely on the fact that all apps will use pkgconfig,
#make eglplatform.h independent of MESA_EGL_NO_X11_HEADER
do_install_append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'egl', 'true', 'false', d)}; then
        sed -i -e 's/^#if defined(MESA_EGL_NO_X11_HEADERS)$/#if defined(MESA_EGL_NO_X11_HEADERS) || ${@bb.utils.contains('PACKAGECONFIG', 'x11', '0', '1', d)}/' ${D}${includedir}/EGL/eglplatform.h
    fi
}
