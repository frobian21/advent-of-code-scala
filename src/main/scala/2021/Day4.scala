object Day4 {
  case class Passport(
    byr: String,
    iyr: String,
    eyr: String,
    hgt: String,
    hcl: String,
    ecl: String,
    pid: String,
    cid: Option[String]) {
    def validate: Boolean =
      List(
        byr.matches("(19[2-9][0-9]|200[0-2])"),
        iyr.matches("20(1[0-9]|20)"),
        eyr.matches("20(2[0-9]|30)"),
        hgt.matches("1([5-8][0-9]|9[0-3])cm|(59|6[0-9]|7[0-6])in"),
        hcl.matches("#[0-9a-f]{6}"),
        ecl.matches("amb|blu|brn|gry|grn|hzl|oth"),
        pid.matches("[0-9]{9}")
      ).foldLeft(true)(_ && _)
  }
  def createPassport(map: Map[String, String]): Option[Passport] =
    for {
      byr <- map.get("byr")
      iyr <- map.get("iyr")
      eyr <- map.get("eyr")
      hgt <- map.get("hgt")
      hcl <- map.get("hcl")
      ecl <- map.get("ecl")
      pid <- map.get("pid")
    } yield Passport(byr, iyr, eyr, hgt, hcl, ecl, pid, cid = map.get("cid"))
  def parseDocumentToPassport(document: String): List[Option[Passport]] =
    document
      .split("[\\r?\\n]{3}")
      .filter(_.nonEmpty)
      .map(
        passport =>
          createPassport(
            """([^\s]+):([^\s]+)""".r
              .findAllIn(passport)
              .matchData
              .map(m => m.group(1) -> m.group(2))
              .toMap))
      .toList

  def countValidDocuments(document: String) =
    parseDocumentToPassport(document).count(_.isDefined)
  def validateDocuments(document: String) =
    parseDocumentToPassport(document).count(_.map(_.validate).getOrElse(false))

}
