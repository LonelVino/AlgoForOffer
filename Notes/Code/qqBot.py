'''
Author: your name
Date: 2020-09-09 10:51:19
LastEditTime: 2020-09-09 10:52:12
LastEditors: Please set LastEditors
Description: In User Settings Edit
FilePath: \Desktopf:\桌面缓存\学习资料等\大二下\学习笔记\LeetCode\Code\Python\qqBot.py
'''
import time
import string
import hashlib
import requests
import random
import urllib

def tencent(msg):
    APPID = '2156537700'
    APPKEY = 'Gf0HognbskGukVtj'
    url = 'https://api.ai.qq.com/fcgi-bin/nlp/nlp_textchat'
    params = {
        'app_id': APPID,
        'time_stamp': str(int(time.time())),
        'nonce_str': ''.join(random.choice(string.ascii_letters + string.digits) for x in range(16)),
        'session': '10000'.encode('utf-8'),
        'question': msg.encode('utf-8')
    }
    sign_before = ''
    for key in sorted(params):
        # 键值拼接过程value部分需要URL编码，URL编码算法用大写字母，例如%E8。quote默认大写。
        sign_before += '{}={}&'.format(key, urllib.parse.quote(params[key], safe=''))
        # 将应用密钥以app_key为键名，拼接到字符串sign_before末尾
    sign_before += 'app_key={}'.format(APPKEY)
​
    # 对字符串sign_before进行MD5运算，得到接口请求签名
    sign = hashlib.md5(sign_before.encode('UTF-8')).hexdigest().upper()
    params['sign'] = sign
    # print(params)
    html = requests.post(url, data=params).json()
    return html['data']['answer']
msg= '我好看吗'
print("原话>>", msg)
res = tencent(msg)
print("腾讯>>", res)
