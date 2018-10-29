package com.wecash.nevermore.httpclient;


/**
 * 任务状态接口，用来和某个具体的业务的自定义状态拼接成全状态
 */
public interface Status  {

    //以下提供一些默认的状态枚举
    Status BEGIN = create("BEGIN");
    Status INIT_FINISH = create("INIT_FINISH");
    Status ALLOC_FINISH = create("ALLOC_FINISH");
    Status WRAP_FINISH = create("WRAP_FINISH");
    Status PARSE_FINISH = create("PARSE_FINISH");
    Status GET_PROXY = create("GET_PROXY");
    Status GET_SEED = create("GET_SEED");
    Status MIDDLE = create("MIDDLE");
    Status SUCCESS = create("SUCCESS");
    Status FAIL = create("FAIL");
    Status EXCEPTION = create("EXCEPTION");
    Status RETRY = create("RETRY");

    String status();

    public static Status create(String name) {
        return new BizStatusMachine(name) {
        };
    }

    abstract class BizStatusMachine implements Status {
        protected String status;

        public BizStatusMachine(String name) {
            this.status = name;
        }

        @Override
        public String toString() {
            return status;
        }

        @Override
        public String status() {
            return status;
        }
    }
}
