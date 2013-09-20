package se.su.it.grails.plugins.trimmer

/**
 * Thanks to Burt Beckwith for this code!
 * Every last line ripped from Programming Grails!
 */

class InputTrimmingFilters {
  def filters = {
    blankToNullAndTrim(controller: '*', action: '*') {
      before = {
        if (request.post) {
          convertBlanksToNullsAndTrim(params as Map)
        }
        true
      }
    }
  }

  private static void convertBlanksToNullsAndTrim(Map map) {
    // copy to avoid ConcurrentModificationException
    def keys = [] + map.keySet()

    for (name in keys) {
      def value = map[name]

      if (value instanceof String) {
        value = value.trim()
        if (value.length() == 0) {
          // don't remove - explicity set to null
          map[name] = null
        } else {
          // update if trimmed
          map[name] = value
        }
      }
      else if (value instanceof Map) {
        convertBlanksToNullsAndTrim(value)
      }
    }
  }
}
