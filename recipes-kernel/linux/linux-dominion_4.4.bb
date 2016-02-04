require linux.inc

DESCRIPTION = "Linux kernel"
KERNEL_IMAGETYPE ?= "zImage"

COMPATIBLE_MACHINE = "(dominion-old|dominion|beast|macbook|soekris-net6501|arietta-g25|macbook)"

FILESPATH =. "${FILE_DIRNAME}/linux-dominion-4.4:${FILE_DIRNAME}/linux-dominion-4.4/${MACHINE}:"

S = "${WORKDIR}/git"

PV = "4.4.1"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;branch=linux-4.4.y"
SRCREV_pn-${PN} = "f1ab5eafa3625b41c74326a1994a820ff805d5b2"

SRC_URI += " \
             file://0001-wireless-populate-db.txt.patch \
             file://0002-btusb-add-USB-ID-for-ASUS-X79-DELUXE-onboard-BT.patch \
             file://0003-Acme-boards-patches.patch \
             file://0004-ARM-DTS-arietta-G25-ttyS2-SPI0-ADC-on-expansion-head.patch \
             file://0005-ARM-DTS-acme-arietta-use-button-as-power-button.patch \
             file://0006-ARM-DTS-acme-arietta-drop-chosen-node.patch \
             file://defconfig \
           "
