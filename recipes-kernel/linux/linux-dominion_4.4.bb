require linux.inc

DESCRIPTION = "Linux kernel"
KERNEL_IMAGETYPE ?= "zImage"

COMPATIBLE_MACHINE = "(dominion-old|dominion|beast|macbook|soekris-net6501|arietta-g25|macbook)"

FILESPATH =. "${FILE_DIRNAME}/linux-dominion-4.4:${FILE_DIRNAME}/linux-dominion-4.4/${MACHINE}:"

S = "${WORKDIR}/git"

PV = "4.4.0"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;branch=linux-4.4.y"
SRCREV_pn-${PN} = "afd2ff9b7e1b367172f18ba7f693dfb62bdcb2dc"

SRC_URI += " \
             file://defconfig \
           "
