package amc.mb.rsassociations.services.development;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import amc.mb.rsassociations.enums.RSFunction;

@Service
@Validated
public class TestService {

	public static final String INNER_TEST_SERVICE_BEAN_NAME = "innerTestService";

	private final String name;

	private final TestService innerTestService;

	public TestService(@Autowired(required = false) @Qualifier(INNER_TEST_SERVICE_BEAN_NAME) TestService innerTestService) {
		this.name = innerTestService == null ? "Inner service" : "Outer service";
		System.out.println("1. Create TestService: " + this.name);
		this.innerTestService = innerTestService;
	}

	public void doItWithArray(@NotNull List<String> args) {
		System.out.println(name + "===Aantal===>" + args.size());
		args.forEach(System.out::println);
		args.forEach(innerTestService::doItWithValue);
	}

	public void doItWithValue(@NotNull String arg) {
		System.out.println(name + "---->" + arg);
	}

	public static void main(String[] args) {
		System.out.println(RSFunction.BUSINESS_DEVELOPER.ordinal());
	}
}
