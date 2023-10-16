# 访问 skoala 服务 api
# 为了方便测试验证，每执行完一次分支事务，睡眠10s
# 为了方便测试验证，该 api 共耗时 20s 左右
curl 'http://10.6.222.21:30096/lanes/err' \
--header 'Content-Type: application/json' \
--data '{
  "name" : "daocloud11",
  "label" : "test11"
}'
