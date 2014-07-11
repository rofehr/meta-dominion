require linux.inc

DESCRIPTION = "Linux kernel"
KERNEL_IMAGETYPE ?= "zImage"

COMPATIBLE_MACHINE = "(dominion|beast|macbook|soekris-net6501|arietta-g25)"

FILESPATH =. "${FILE_DIRNAME}/linux-dominion-3.15:${FILE_DIRNAME}/linux-dominion-3.15/${MACHINE}:"

S = "${WORKDIR}/git"

PV = "3.15.5"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;branch=linux-3.15.y"
SRCREV_pn-${PN} = "a484f1136a4a2e00b6a70567cf978f4ba52ba654"

SRC_URI += " \
             file://defconfig \
             file://0001-x86-quirks-soekris-HPET.patch \
             file://0002-block-cgroups-kconfig-build-bits-for-BFQ-v7r4-3.15.patch \
             file://0003-block-introduce-the-BFQ-v7r4-I-O-sched-for-3.15.patch \
             file://0004-block-bfq-add-Early-Queue-Merge-EQM-to-BFQ-v7r4-for-.patch \
             file://0005-Btrfs-fix-deadlocks-with-trylock-on-tree-nodes.patch \
             file://0001-Acme-boards-patches.patch \
             file://0010-video-st7735fb-add-st7735-framebuffer-driver.patch \
            "
