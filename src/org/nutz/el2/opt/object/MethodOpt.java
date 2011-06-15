package org.nutz.el2.opt.object;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.nutz.el2.Operator;
import org.nutz.el2.obj.IdentifierObj;
import org.nutz.el2.opt.TwoTernary;
import org.nutz.lang.Mirror;


/**
 * 方法体封装.
 * 主要是把方法的左括号做为边界
 * @author juqkai(juqkai@gmail.com)
 *
 */
public class MethodOpt extends TwoTernary {

	public int fetchPriority() {
		return 1;
	}
	
	public void wrap(Queue<Object> rpn) {
		if(rpn.peek() instanceof AccessOpt){
			left = rpn.poll();
			return;
		}
		super.wrap(rpn);
	}
	
	public Object calculate() {
		if(!(left instanceof AccessOpt)){
			//@ JKTODO 添加自定义方法的调用
			return null;
		}
		AccessOpt lval = (AccessOpt) left;
		Object[] objs = (Object[]) lval.calculate();
		Object obj = objs[0];
		if(objs[0] instanceof IdentifierObj){
			obj = ((IdentifierObj) objs[0]).fetchVal();
		}
		Object method = objs[1];
		
		List<Object> rvals = fetchParam();
		
		Mirror<?> me = Mirror.me(obj);
		if(rvals.isEmpty()){
			return me.invoke(obj, method.toString());
		}
		for(int i = 0; i < rvals.size(); i ++){
			if(rvals.get(i) instanceof Operator){
				rvals.set(i, ((Operator)rvals.get(i)).calculate());
			}
		}
		return me.invoke(obj, method.toString(), rvals.toArray());
	}
	
	/**
	 * 取得方法执行的参数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Object> fetchParam(){
		List<Object> rvals = new ArrayList<Object>();
		if(right != null){
			if(right instanceof CommaOpt){
				rvals = (List<Object>) ((CommaOpt) right).calculate();
			} else {
				rvals.add(calculateItem(right));
			}
		}
		return rvals;
	}
	
	public String fetchSelf() {
		return "method";
	}

}
