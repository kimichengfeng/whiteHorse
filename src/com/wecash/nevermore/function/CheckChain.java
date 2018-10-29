package com.wecash.nevermore.function;

import com.google.common.base.Predicate;

/**
 * @desc 实现判断链的链式调用
 * @demo 检查一个对象的链式调用 CheckChain.start().isTrue(Predicate<T> predicate).isFalse(Predicate<T> predicate).result();
 * @demo 检查多个对象的链式调用 CheckChain.start().isTrue(T t,Predicate<T> predicate).isFalse(T t,Predicate<T> predicate).result;
 */
public abstract class CheckChain {

    boolean result;
    Object checkObj;

    public static CheckChain start() {
        return new MultiChain();
    }

    public static <T> CheckChain start(T checkObj) {
        return new SingleChain(checkObj);
    }

    public abstract <T> CheckChain isTrue(Predicate<T> predicate);

    public abstract <T> CheckChain isFalse(Predicate<T> predicate);

    public abstract <T> CheckChain isTrue(T t, Predicate<T> predicate);

    public abstract <T> CheckChain isFalse(T t, Predicate<T> predicate);

    public CheckChain isTrue(boolean expression) {
        if (this.result) {
            this.result = expression;
            return this;
        }
        return this;
    }

    public CheckChain isFalse(boolean expression) {
        if (this.result) {
            this.result = !expression;
            return this;
        }
        return this;
    }

    public CheckChain AND(boolean leftExpression, boolean rightExpression) {
        if (this.result) {
            this.result = leftExpression && rightExpression;
            return this;
        }
        return this;
    }

    public CheckChain OR(boolean leftExpression, boolean rightExpression) {
        if (this.result) {
            this.result = leftExpression || rightExpression;
            return this;
        }
        return this;
    }

    public CheckChain ANDALL(boolean... expressions) {
        if (this.result) {
            for (boolean expression : expressions) {
                if (!this.result) {
                    break;
                }
                this.result = this.result && expression;
            }
            return this;
        }
        return this;
    }

    public CheckChain ORALL(boolean... expressions) {
        if (this.result) {
            for (boolean expression : expressions) {
                if (this.result) {
                    break;
                }
                this.result = this.result || expression;
            }
            return this;
        }
        return this;
    }

    public boolean result() {
        return this.result;
    }

    private static final class SingleChain extends CheckChain {
        protected SingleChain(Object checkObj) {
            this.result = true;
            this.checkObj = checkObj;
        }

        @Override
        public <T> CheckChain isTrue(Predicate<T> predicate) {
            if (this.result) {
                this.result = predicate.apply((T) checkObj);
                return this;
            }
            return this;
        }

        @Override
        public <T> CheckChain isFalse(Predicate<T> predicate) {
            if (this.result) {
                this.result = !predicate.apply((T) checkObj);
                return this;
            }
            return this;
        }

        @Deprecated
        @Override
        public <T> CheckChain isTrue(T t, Predicate<T> predicate) {
            if (this.result) {
                this.result = predicate.apply(t);
                return this;
            }
            return this;
        }

        @Deprecated
        @Override
        public <T> CheckChain isFalse(T t, Predicate<T> predicate) {
            if (this.result) {
                this.result = !predicate.apply(t);
                return this;
            }
            return this;
        }

    }

    private static final class MultiChain extends CheckChain {
        protected MultiChain() {
            this.result = true;
        }

        @Deprecated
        @Override
        public <T> CheckChain isTrue(Predicate<T> predicate) {
            return this;
        }

        @Deprecated
        @Override
        public <T> CheckChain isFalse(Predicate<T> predicate) {
            return this;
        }

        @Override
        public <T> CheckChain isTrue(T t, Predicate<T> predicate) {
            if (this.result) {
                this.result = predicate.apply(t);
                return this;
            }
            return this;
        }

        @Override
        public <T> CheckChain isFalse(T t, Predicate<T> predicate) {
            if (this.result) {
                this.result = !predicate.apply(t);
                return this;
            }
            return this;
        }

    }
}
