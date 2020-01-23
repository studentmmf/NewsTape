package ru.sibers.core;

public enum Count {

    TEN(10),
    TWENTY(20),
    FIFTY(50);

    private final int count;
    public static Count getValue(String s) {
        switch (s) {
            case "20": return TWENTY;
            case "50": return FIFTY;
            case "10": return TEN;
            default:
                return TEN;
        }
    }

    Count(int count) {
        this.count = count;
    }

	public int getCount() {
		return count;
	}

    
}
