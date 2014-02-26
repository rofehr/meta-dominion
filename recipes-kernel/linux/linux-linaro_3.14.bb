require linux.inc

DESCRIPTION = "Linux kernel"
KERNEL_IMAGETYPE ?= "zImage"

COMPATIBLE_MACHINE = "(arndale-octa)"

FILESPATH =. "${FILE_DIRNAME}/linux-linaro:${FILE_DIRNAME}/linux-linaro/${MACHINE}:"

S = "${WORKDIR}/git"

PV = "3.13+3.14-rc3"

SRC_URI = "git://git.linaro.org/kernel/linux-linaro-tracking.git;branch=linux-linaro"
SRCREV_pn-${PN} = "68be59587608134e35b5f2cc8f200713c7db2b61"

SRC_URI += " \
             file://defconfig \
"
