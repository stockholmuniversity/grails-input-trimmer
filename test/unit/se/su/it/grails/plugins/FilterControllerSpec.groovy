package se.su.it.grails.plugins

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import se.su.it.grails.plugins.trimmer.InputTrimmingFilters
import spock.lang.Specification

@TestFor(FilterController)
@Mock(InputTrimmingFilters)
class FilterControllerSpec extends Specification {
  def "index: Trims strings on POST"() {
    given:
    request.method = "POST"
    params.someParam = "   kfkfke    "
    when:
    withFilters(action:'index') {
      controller.index()
    }

    then:
    params.someParam == "kfkfke"
  }

  def "index: Doesn't trim strings on get"() {
    given:
    request.method = "GET"
    params.someParam = "   kfkfke    "
    when:
    withFilters(action:'index') {
      controller.index()
    }

    then:
    params.someParam == "   kfkfke    "
  }

}

