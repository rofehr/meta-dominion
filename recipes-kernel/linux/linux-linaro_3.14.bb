require linux.inc

DESCRIPTION = "Linux kernel"
KERNEL_IMAGETYPE ?= "zImage"

COMPATIBLE_MACHINE = "(arndale-octa)"

FILESPATH =. "${FILE_DIRNAME}/linux-linaro:${FILE_DIRNAME}/linux-linaro/${MACHINE}:"

S = "${WORKDIR}/git"

PV = "3.13+3.14-rc7"

SRC_URI = "git://git.linaro.org/kernel/linux-linaro-tracking.git;branch=linux-linaro"
SRCREV_pn-${PN} = "fb3934ea7040c7756d15a75de4e43a344cffe536"

SRC_URI += " \
             file://defconfig \
"
