
/*Given a screen with a given width, height and supported min/max font size, determine the max font a given string can be 
displayed in. Word or character can’t be broken. Imagine a method getWidth(char c, int fontSize) and 
getHeight(int fontSize) are given
    二分搜[minFont, maxFont]这个范围
找到mid，把string里的都按mid font放到screen中，放不下，往左，放下，往右
*/



class Solution {

	int width;
	int height;
	List<Integer> fonts;
	Solution (int width, int height, List<Integer> fonts) {
		this.width = width;
		this.height = height;
		this.fonts = fonts;
	}
	public int getWidth (char c, int fontSize) {

	}
	public int getHeight (int fontSize) {

	}
	public int canFitInt (String input) {
		if (input == null || input.length() == 0) {
			return fonts.get(fonts.size() - 1);
		}
		int left = 0, right = fonts.size() - 1;

		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (helper(input, mid)) {
				left = mid;
			} else {
				right = mid;
			}
		}
		if (helper(input, right)) {
			return right;
		}
		if (helper(input, left)) {
			return left;
		}
		return -1;
	}

	public boolean helper (String input, int fontSize) {
		int start = 0;
		int curHeight = 0;
		int curWidth = 0;
		for (int i = 0; i < input.length(); i++) {
			// add space 
			while (i < input.length() && input.charAt(i) == ' ') {
				int charSize = getWidth(' ', fontSize);
				// 如果加上這個空格後會超過這行的寬度
				if (curWidth + charSize > width) {
					// 換行
					curHeight += getHeight(fontSize);
					// 如果換行後超過高度 代表超出螢幕
					if (curHeight > height) {
						return false;
					}
					// 換行後寬度要重設為只有一個空格的寬
					curWidth = charSize;
				} else {
					// 沒超過寬度, 繼續往右邊放
					curWidth += charSize;
				}
			}
			// add word 要分空格跟字元是因為字元組成的word不能被切斷在一行, 例如以下
			// 要是word
			// 不能是wor , 而d跑到下一行
			int wordLen = 0;
			while (i < input.length() && input.charAt(i) != ' ') {
				wordLen += getWidth(input.charAt(i), fontSize);
			}
			// 如果一個word就超過一行, 代表這種字體太大了直接false
			if (wordLen > width) {
				return false;
			}

			// 把該字的長度加上空格的長度看有沒有超過螢幕寬. 超過的話開一行新的
			if (curWidth + wordLen > width) {
				curHeight += getHeight(fontSize);
				// 開新的行超出螢幕大小, false
				if (curHeight > height) {
					return false;
				}
				curWidth = wordLen;
			} else {
				curWidth += wordLen;

			}
			
		}
		return true;
	}
}