require linux.inc

DESCRIPTION = "Linux kernel"
KERNEL_IMAGETYPE ?= "zImage"

COMPATIBLE_MACHINE = "(arndale-octa)"

FILESPATH =. "${FILE_DIRNAME}/linux-linaro:${FILE_DIRNAME}/linux-linaro/${MACHINE}:"

S = "${WORKDIR}/git"

PV = "3.13+3.14-rc7"

SRC_URI = "git://git.linaro.org/kernel/linux-linaro-tracking.git;protocol=http;branch=linux-linaro"
SRCREV_pn-${PN} = "88236400e1c7d4dd4b59f137cf496aae215af7aa"

SRC_URI += " \
             file://defconfig \
"
