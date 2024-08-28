class Dashing.Survey extends Dashing.Widget

  @accessor 'updatedAtMessage', ->
    if updatedAt = @get('updatedAt')
      timestamp = new Date(updatedAt * 1000)
      year = timestamp.getFullYear()
      month = timestamp.getMonth()
      day = timestamp.getDate()
      hours = timestamp.getHours()
      minutes = ("0" + timestamp.getMinutes()).slice(-2)
      # seconds = ("0" + timestamp.getSeconds()).slice(-2)
      "Last updated: 
      #{day}/#{month}/#{year} #{hours}:#{minutes}"
