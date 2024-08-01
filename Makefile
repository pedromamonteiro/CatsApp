GRADLE_WRAPPER=./gradlew
REPORT_DIR=app/build/reports/jacoco/jacocoDebugTestReport/html

jacoco_report:
	$(GRADLE_WRAPPER) clean jacocoDebugTestReport
	open $(REPORT_DIR)/index.html
