class Dashing.Number extends Dashing.Widget
  @accessor 'current', Dashing.AnimatedValue
  
  @accessor 'updatedAtMessage', ->
    if updatedAt = @get('updatedAt')
      timestamp = new Date(updatedAt * 1000)
      year = timestamp.getFullYear()
      month = timestamp.getMonth() + 1
      day = timestamp.getDate()
      hours = timestamp.getHours()
      minutes = ("0" + timestamp.getMinutes()).slice(-2)
      # seconds = ("0" + timestamp.getSeconds()).slice(-2)
      "Last updated: 
      #{day}/#{month}/#{year} #{hours}:#{minutes}"

  @accessor 'difference', ->
    if @get('last')
      last = parseInt(@get('last'))
      current = parseInt(@get('current'))
      if last != 0
        diff = Math.abs(Math.round((current - last) / last * 100))
        if (current > last)
          "  #{diff}% higher than average"
        else
          "  #{diff}% lower than average"
    else
      ""

  @accessor 'arrow', ->
    if @get('last')
      if parseInt(@get('current')) > parseInt(@get('last')) then 'fa fa-arrow-up' else 'fa fa-arrow-down'

  onData: (data) ->
    node = $(@node)
    value = parseInt data.current
    cool = parseInt node.data "cool"
    warm = parseInt node.data "warm"
    
    low = Math.min(@cool, @warm)
    high = Math.max(@cool, @warm)
    
    level = switch
      when value >= high then 0
      when value <= low then 2
      else 1
        
    backgroundClass = "hotness#{level}"
    lastClass = @get "lastClass"
    node.toggleClass "#{lastClass} #{backgroundClass}"
    @set "lastClass", backgroundClass
    
    if data.status
      # clear existing "status-*" classes
      $(@get('node')).attr 'class', (i,c) ->
        c.replace /\bstatus-\S+/g, ''
      # add new class
      $(@get('node')).addClass "status-#{data.status}"
