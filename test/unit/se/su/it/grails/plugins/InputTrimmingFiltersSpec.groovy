package se.su.it.grails.plugins

import se.su.it.grails.plugins.trimmer.InputTrimmingFilters
import spock.lang.Specification

class InputTrimmingFiltersSpec extends Specification {
  def "testConvertBlanksToNullsAndTrim"() {
    given:
    def params = ["name":"  foo  ", "bar":["name":"   foo  "]]
    InputTrimmingFilters.convertBlanksToNullsAndTrim(params)
    expect:
    params == ["name":"foo", "bar":["name":"foo"]]
  }
}
