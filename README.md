## 事前準備

DBの接続情報は環境変数（.envファイル）を参照するようにしています。
.envはGit管理対象外にしているので、
ローカルで動かす際は、以下のような内容の.envをルートディレクトリに作成してください。

```
DB_NAME=sample_db
DB_USER=user
DB_ROOT_PASS=pass
DB_PASS=pass
DB_PORT=3307
TZ=Asia/Tokyo

TEST_DB_NAME=test_db
TEST_DB_PORT=3308
```

## アプリの起動方法
ルートディレクトリで、docker compose up -d　を実行する。

## アプリの停止方法
ルートディレクトリで、docker compose down -v　を実行する。
