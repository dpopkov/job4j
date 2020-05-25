package ru.job4j.jmm.gc;

@SuppressWarnings("unused")
class MemoryInfo {
    private final long unit;

    public MemoryInfo(long unit) {
        this.unit = unit;
    }

    public void print() {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("##### Heap utilization statistics [MB] #####");
        printMemory("Used Memory :", (runtime.totalMemory() - runtime.freeMemory()), unit);
        printMemory("Free Memory :", runtime.freeMemory(), unit);
        printMemory("Total Memory:", runtime.totalMemory(), unit);
        printMemory("Max Memory  :", runtime.maxMemory(), unit);
    }

    private void printMemory(String title, long amount, long unit) {
        System.out.printf("# %s %10d%n", title, (amount / unit));
    }
}
