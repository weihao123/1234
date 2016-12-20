package com.ssmo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssmo.pojo.Car;
import com.ssmo.service.CarService;

//spring ע�������ҳ�������(������)
@Controller
public class CarController {
  @Resource(name = "carService")
  private CarService carService;

  /**
   * Spring MVC �ᰴ����������� POJO �����������Զ�ƥ�䣬 �Զ�Ϊ�ö����������ֵ��֧�ּ������ԡ�
   * �磺dept.deptId��dept.address.tel ��
   */
  @RequestMapping("carController_save")
  public String save(Car car) {
    if (car != null && car.getId() != null) {
      carService.modify(car);
    } else {
      carService.add(car);
    }
    return "redirect:carController_list";
  }

  @RequestMapping(value = "carController_findById")
  public ModelAndView findById(@RequestParam(value = "id", required = true) Integer id) {

	// ʵ����ģ�ͺ���ͼ����,�������߼���ͼ��
    ModelAndView modelAndView = new ModelAndView("caredit");
    // ModelAndView modelAndView = new ModelAndView();
    // modelAndView.setViewName("caredit");
    Car car = carService.find(id);
 // ����ģ������
    modelAndView.addObject("car", car);

    return modelAndView;
  }

  @RequestMapping("carController_remove")
  public String remove(@RequestParam(value = "id", required = true) Integer id) {

	// ��������ѡ,������Ĭ��ֵ
	    // value="id",required=false,defaultValue="3"
	    // @RequestParam("id") �൱�� id = request.getParameter("id");
    carService.remove(id);
    return "redirect:carController_list";
  }

  /**
   * 1. ʹ�� @RequestMapping ע����ӳ������� URL 2. ����ֵ��ͨ����ͼ����������Ϊʵ�ʵ�������ͼ, ����
   * InternalResourceViewResolver ��ͼ������, �������µĽ���: ͨ�� prefix + returnVal + suffix
   * �����ķ�ʽ�õ�ʵ�ʵ�������ͼ, Ȼ����ת������
   * 
   * /carlist.jsp
   * 
   * @return
   */
  @RequestMapping("carController_list")
  public String list(ModelMap modelMap) {
	  // ����ģ��(ҵ���߼���,����ģ������)
    List<Car> cars = carService.find();

 // modelMap �൱�� map,request������
    modelMap.put("cars", cars);
    // �߼���ͼ��
    return "carlist";
  }
  /*
   * public ModelAndView list() { // ����ģ��(ҵ���߼���,����ģ������) List<Car> cars =
   * carService.find();
   * 
   * // ʵ����ģ�ͺ���ͼ���� // ModelAndView modelAndView = new ModelAndView(); //
   * ����(�߼�)��ͼ // modelAndView.setViewName("carlist");
   * 
   * // ʵ����ģ�ͺ���ͼ��������ͼ�� ModelAndView modelAndView = new ModelAndView("carlist");
   * // ����ģ������ modelAndView.addObject("cars", cars);
   * 
   * return modelAndView; }
   */

}
