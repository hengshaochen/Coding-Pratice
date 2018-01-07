class MyCalendar {
    class Pair {
        int start, end;
        Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    List<Pair> event;
    public MyCalendar() {
        event = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
        for (int i = 0; i < event.size(); i++) {
            if (Math.max(event.get(i).start, start) < Math.min(event.get(i).end, end)) {
                // MAX(start1, start2) < MIN(end1, end2) 成立代表有overlap
                return false;
            }
        }
        event.add(new Pair(start, end));
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */

 // 法2. TreeMap
 class MyCalendar {
    TreeMap<Integer, Integer> event;
    public MyCalendar() {
        event = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        Integer lowerBound = event.floorKey(start);
        if (lowerBound != null && event.get(lowerBound) > start) {
            return false;
        }
        Integer upperBound = event.ceilingKey(start);
        if (upperBound != null && upperBound < end) {
            return false;
        }
        event.put(start, end);
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */