package org.mall;

import com.ql.util.express.ExpressRunner;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class JayTest{

    @Test
    public void test1() throws Exception {
        //定义表达式，相当于 1+(22+22)+(2+2)
        String exp = " 1 addT 22 addT 2";
        ExpressRunner runner = new ExpressRunner();
        //定义操作符addT，其实现为AddTwiceOperator
        runner.addOperator("addT", new AddTwiceOperator());
        //执行表达式，并将结果赋给r
        int r = (Integer)runner.execute(exp,null,null,false,false);
        System.out.println(r);
        Assert.assertTrue("操作符执行错误",r==49);
    }


}
