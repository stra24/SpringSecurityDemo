## 事前準備

DBの情報はセキュリティの観点から環境変数（.envファイル）で管理しています。
.envはGit管理対象外にしているので、
ローカルで動かす際は、以下のような内容の.envをルートディレクトリに作成してください。

DB_NAME=sample_db <br>
DB_USER=user <br>
DB_ROOT_PASS=pass <br>
DB_PASS=pass <br>
DB_PORT=3307 <br>
TZ=Asia/Tokyo <br>
 <br>
TEST_DB_NAME=test_db <br>
TEST_DB_PORT=3308 <br>

## アプリの起動方法
ルートディレクトリで、docker compose up -d　を実行する。

## アプリの停止方法
ルートディレクトリで、docker compose down -v　を実行する。
