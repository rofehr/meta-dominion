require linux.inc

DESCRIPTION = "Linux kernel"
KERNEL_IMAGETYPE ?= "zImage"

COMPATIBLE_MACHINE = "(dominion|beast|macbook|soekris-net6501|arietta-g25|macbook)"

FILESPATH =. "${FILE_DIRNAME}/linux-dominion-4.3:${FILE_DIRNAME}/linux-dominion-4.3/${MACHINE}:"

S = "${WORKDIR}/git"

PV = "4.3.3"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;branch=linux-4.3.y"
SRCREV_pn-${PN} = "35483418917d63df90bae5b2d0b7b047d7ed8ec7"

SRC_URI += " \
             file://defconfig \
             file://0001-wireless-populate-db.txt.patch \
             file://0002-btusb-add-USB-ID-for-ASUS-X79-DELUXE-onboard-BT.patch \
             file://0003-block-cgroups-kconfig-build-bits-for-BFQ-v7r8-4.3.patch \
             file://0004-block-introduce-the-BFQ-v7r8-I-O-sched-for-4.3.patch \
             file://0005-block-bfq-add-Early-Queue-Merge-EQM-to-BFQ-v7r8-for-.patch \
             file://0006-Acme-boards-patches.patch \
             file://0007-ARM-DTS-arietta-G25-ttyS2-SPI0-ADC-on-expansion-head.patch \
             file://0008-ARM-DTS-acme-arietta-use-button-as-power-button.patch \
             file://0009-ARM-DTS-acme-arietta-drop-chosen-node.patch \
           "
