package com.tuplescale.graph.service;



import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.tuplescale.graph.model.PlanningVertex;

import lombok.extern.slf4j.Slf4j;

@Component
@Lazy
@Slf4j
public class NodeProcessor  {

	int count=0;
	public void process(PlanningVertex item,Set<PlanningVertex> dependents) throws Exception {
		
		List<Double> valueList=getDependentValues(dependents);
		if(!valueList.isEmpty()) {
				switch(item.getExpressionName()) {
					case "xequalsy":
						item.setValue(valueList.get(0));
						break;
					case "xplusy":
						item.setValue(valueList.get(0)+valueList.get(1));
						break;
					case "xmultipliedy":
						item.setValue(valueList.get(0)*valueList.get(1));
						break;
					case "xminusy":
						item.setValue(valueList.get(0)-valueList.get(1));
						break;
					case "xmultipliedconstant":
						costantExpession(item, valueList);
						break;
					case "monthtoweekv1"://(value1/30*7)+value2
						item.setValue((valueList.get(0)/30*7)+valueList.get(1));
						break;
					case "xminusymultiz":
						item.setValue(valueList.get(0)-(valueList.get(1)*valueList.get(2)));
						break;
					case "sum_xyz":
						item.setValue(valueList.get(0)+valueList.get(1)+valueList.get(2));
						break;
					case "xminussumab":
						item.setValue(valueList.get(0)-(valueList.get(1)+valueList.get(2)));
						break;
					case "sum_abcd":
						item.setValue(valueList.get(0)+valueList.get(1)+valueList.get(2)+valueList.get(3));
						break;
					case "sumxyminussumab"://"(value1+value2)-(value3+value4)"
						item.setValue((valueList.get(0)+valueList.get(1))-(valueList.get(2)+valueList.get(3)));
						break;
					case "xminussumabc":
						item.setValue(valueList.get(0)-(valueList.get(1)+valueList.get(2)+valueList.get(3)));
						break;
					case "sumxyminussumabc"://(value1+value2)-(value3+value4+value5)
						item.setValue((valueList.get(0)+valueList.get(1))-(valueList.get(2)+valueList.get(3)+valueList.get(4)));
						break;
					case "ifcasev2":
						ifCaseV2(item, valueList);
						break;
					case "dailyavgv1":				
						dailyAvgV1(item, valueList);
						break;
					case "dailyavgv2":				
						dailyAvgV2(item, valueList);
						break;
					case "dailyavgv3":
						dailyAvgV3(item, valueList);
						break;
					case 	"xdividey":
						xDividey(item, valueList);
						break;
					case "xdivideyv1":
						xDivideyV1(item, valueList);
						break;
						default: item.setValue(10.0);
							break;
				}
			}
		else {
			item.setValue(item.getValue()+0.0);
		}
		
		
	}
	
	
	// case when (value3 - value1 - value2) < value4 then */ value4 /*else*/ (value3 - value1 - value2) /* end */				
	private void ifCaseV2(PlanningVertex item,List<Double> valueList) {
		double val1=valueList.get(0);
		double val2=valueList.get(1);
		double val3=valueList.get(2);
		double val4=valueList.get(3);
		if((val3-val1-val2)<val4) 
			item.setValue(val4);
		else
			item.setValue(val3-val1-val2);
	}
	//case when (value1) > 0.00001 then */ (value1 * value2) / 30 /* else */ 0 /* end */
	private void dailyAvgV1(PlanningVertex item,List<Double> valueList) {
		double val1=valueList.get(0);
		double val2=valueList.get(1);
		if(val1>0.00001)
			item.setValue((val1*val2)/30);
		else
			item.setValue(0.0);
	}
	//case when (value2) > 0 then */ (value1) * 30 / value2 /* else */ 0 /* end */
	private void dailyAvgV2(PlanningVertex item,List<Double> valueList) {
		double val1=valueList.get(0);
		double val2=valueList.get(1);
		if(val2>0)
			item.setValue(val1*30/val2);
		else
			item.setValue(0.0);
	}
	// when (value1 * value2) > 0.00001 then */ (value1 * value2) / 30 /*else*/ 0 /* end */
	private void dailyAvgV3(PlanningVertex item,List<Double> valueList) {
		double val1=valueList.get(0);
		double val2=valueList.get(1);
		if((val1*val2)>0.00001)
			item.setValue(val1*val2/30);
		else
			item.setValue(0.0);
	}
	//when (value2) >  0 then */ value1 / value2 /*  else */ 0 /* end */
	private void xDividey(PlanningVertex item,List<Double> valueList) {
		double val1=valueList.get(0);
		double val2=valueList.get(1);
		if(val2>0)
			item.setValue(val1/val2);
		else
			item.setValue(0.0);
	}
	//(value1) = 1 then */ value2 /* else */ 0 /* end */"
	private void xDivideyV1(PlanningVertex item,List<Double> valueList) {
		double val1=valueList.get(0);
		double val2=valueList.get(1);
		if(val1==1)	
			item.setValue(val2);
		else
			item.setValue(0.0);
	}
	
	public List<Double> getDependentValues(Set<PlanningVertex> dependents) {
		try {
			List<Double> values=new ArrayList<>();
			dependents.forEach((dependent)->{
					//get from dependent value
					values.add(dependent.getValue()+0.0);
			});
			return values;
		}
		catch(IndexOutOfBoundsException iob) {
			log.info("dependent not available");
			return null;
		}
		
	}
	//TODO make this method dynamic
	private void costantExpession(PlanningVertex item,List<Double> valueList) {
		if(item.getExpression().equalsIgnoreCase("value1 * 0.15")) {
			item.setValue(valueList.get(0)*0.15);
		}else if(item.getExpression().equalsIgnoreCase("value1 * 0.05")) {
			item.setValue(valueList.get(0)*0.05);
		}else if(item.getExpression().equalsIgnoreCase("value1 * 0.08")) {
			item.setValue(valueList.get(0)*0.08);
		}else if(item.getExpression().equalsIgnoreCase("value1 * 0.03")) {
			item.setValue(valueList.get(0)*0.05);
		}
	}
}
