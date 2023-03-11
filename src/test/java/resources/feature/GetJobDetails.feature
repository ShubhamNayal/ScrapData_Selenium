@DBTest
Feature: Update database with the job
  Scenario: Add job description, department,location in the database
    Given Go to Tipico job page
    When On landing on the jobs page
    Then fetch all the jobs
    And click on next until all jobs are fetched


