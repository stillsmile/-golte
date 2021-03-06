package com.golte;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.golte.common.data.GetPictureConfigInData;
import com.golte.common.interceptor.SecurityInterceptor;

@Configuration
public class GolteConfig extends WebMvcConfigurationSupport {
	@Autowired
	private GetPictureConfigInData pictureConfig;

	/**
	 * 图片映射
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 配置图片存储路径和图片访问路径的映射
		registry.addResourceHandler(pictureConfig.getServerBasePath() + "/**").addResourceLocations("file:" + pictureConfig.getBasePath() + "/");
		super.addResourceHandlers(registry);
	}

	/**
	 * 跨域配置
	 */
	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

	/**
	 * 拦截器配置
	 * 
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    List<String> url = new ArrayList<String>();
	    url.add("/common/**");
	    url.add("/pc/login");
	    url.add("/pc/logout");
	    url.add("/pc/updatePwd");
	    url.add("/pc/**/select");
	    url.add("/pc/**/export");
		url.add("/pc/contract/getCityList");
		url.add("/pc/contract/getCityListByCenterCity");
		url.add("/pc/contract/getCenterCityList");
		url.add("/pc/contract/getMerchantList");
		url.add("/pc/contract/getProjectList");
		url.add("/pc/contract/getPointList");
		url.add("/pc/contract/getPointTwoList");
		url.add("/pc/contract/getPointThreeList");
		url.add("/pc/contract/getPointChildList");
		url.add("/pc/contract/getResourceList");
		url.add("/pc/contract/getContractList");
		url.add("/pc/contract/getAmount");
		url.add("/pc/payBackRecord/getEmpList");
		url.add("/pc/contract/getDetail");
		url.add("/pc/contract/exportOut");
		url.add("/pc/excitation/exportOut");
		url.add("/pc/business/exportOut");
		url.add("/pc/resource/project/detail/exportOut");
	    url.add("/" + pictureConfig.getServerBasePath() + "/**");
		registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/**").excludePathPatterns(url);
		super.addInterceptors(registry);
	}

	/**
	 * 消息内容转换配置 配置fastJson返回json转换
	 * 
	 * @param converters
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// 调用父类的配置
		super.configureMessageConverters(converters);
		// 创建fastJson消息转换器
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		// 创建配置类
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		// 修改配置返回内容的过滤
		fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue,
		        SerializerFeature.WriteNullStringAsEmpty);
		// 处理中文乱码问题
		List<MediaType> fastMediaTypes = new ArrayList<MediaType>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastConverter.setSupportedMediaTypes(fastMediaTypes);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		// 将fastjson添加到视图消息转换器列表内
		converters.add(fastConverter);
	}
	
	 /**  
     * 文件上传配置  
     * @return  
     */ 
    @Bean 
    public MultipartConfigElement multipartConfigElement() {  
        MultipartConfigFactory factory = new MultipartConfigFactory();  
        //单个文件
        factory.setMaxFileSize("100MB"); //KB,MB  
        //文件总大小
        factory.setMaxRequestSize("100MB");  
        return factory.createMultipartConfig();  
    }  

}
