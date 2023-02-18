class Solution:
    def isValid(self, s: str) -> bool:
        mapping = {")": "(", "]": "[", "}": "{"}
        stack = []
        for char in s:
            if char in mapping:
                top_element = stack.pop() if stack else '#' #  三目运算符b?x:y----stack作为判断条件
                if mapping[char] != top_element:
                    return False
            else:
                stack.append(char)
        return not stack

if __name__ == "__main__":
    ss = Solution()
    s = "{}{}[]"
    output = ss.isValid(s)
    print(output)
