package se.su.it.grails.plugins

import se.su.it.grails.plugins.trimmer.InputTrimmingFilters
import spock.lang.Specification
import spock.lang.Unroll

class InputTrimmingFiltersSpec extends Specification {

  def "ConvertBlanksToNullsAndTrim: with map"() {
    given:
    def params = [name:"  foo  ", bar:[name:"   foo  "],  "kaka": 1]
    InputTrimmingFilters.convertBlanksToNullsAndTrim(params)

    expect:
    params == ["name":"foo", "bar":["name":"foo"], "kaka": 1]
  }
  @Unroll
  def "ConvertBlanksToNullsAndTrim: when trimming \' #param \' we expect => \'#expected\'"() {
    given:
    def params = [:]
    params["newParam"] = param


    when:
    InputTrimmingFilters.convertBlanksToNullsAndTrim(params)

    then:
    params."newParam" == expected

    where:
    param | expected
    ""    | null
    " "   | null
    " x"  | "x"
    " x " | "x"
  }
}
