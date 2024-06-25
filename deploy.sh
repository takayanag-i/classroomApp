#!/bin/bash

# プロジェクトディレクトリを設定
PROJECT_DIR="/Users/r/Documents/DIP24/sukiserv/classroom"
TOMCAT_DIR="/Users/r/apache-tomcat-10.1.24"
WAR_FILE="$PROJECT_DIR/target/classroom.war"

# コマンドライン引数を受け取る
DEBUG_MODE=false
URL_PATH=""

# 引数解析
while [[ $# -gt 0 ]]; do
  key="$1"
  case $key in
    -dbg|--debug)
      DEBUG_MODE=true
      shift # 引数をスキップ
      ;;
    *)
      URL_PATH="$1"
      shift # 引数をスキップ
      ;;
  esac
done

# プロジェクトディレクトリに移動
cd "$PROJECT_DIR"

# Mavenプロジェクトのビルド
echo "Mavenプロジェクトをビルド中..."
mvn clean install

# Tomcatサーバーの停止
echo "Tomcatサーバーを停止中..."
$TOMCAT_DIR/bin/shutdown.sh

# 新しいWARファイルのコピー
echo "新しいWARファイルをTomcatのwebappsディレクトリにコピー中..."
cp "$WAR_FILE" "$TOMCAT_DIR/webapps/"

# Tomcatサーバーの起動
if [ "$DEBUG_MODE" = true ]; then
  echo "Tomcatサーバーをデバッグモードで起動中..."
  export JPDA_OPTS="-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n"
  $TOMCAT_DIR/bin/catalina.sh jpda start
else
  echo "Tomcatサーバーを起動中..."
  $TOMCAT_DIR/bin/startup.sh
fi

# ブラウザでURLを開く
if [ -z "$URL_PATH" ];then
  echo "ブラウザでhttp://localhost:8080/classroom/ にアクセス中..."
  open "http://localhost:8080/classroom/"
else
  echo "ブラウザでhttp://localhost:8080/classroom/$URL_PATH にアクセス中..."
  open "http://localhost:8080/classroom/$URL_PATH"
fi

echo "デプロイが完了しました。"
