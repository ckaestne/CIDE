package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ImportFromEnd2 extends ImportFromEnd {
  public ImportFromEnd2(import_as_name import_as_name, ArrayList<import_as_name> import_as_name1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<import_as_name>("import_as_name", import_as_name),
      new PropertyZeroOrMore<import_as_name>("import_as_name1", import_as_name1)
    }, firstToken, lastToken);
  }
  public ImportFromEnd2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ImportFromEnd2(cloneProperties(),firstToken,lastToken);
  }
  public import_as_name getImport_as_name() {
    return ((PropertyOne<import_as_name>)getProperty("import_as_name")).getValue();
  }
  public ArrayList<import_as_name> getImport_as_name1() {
    return ((PropertyZeroOrMore<import_as_name>)getProperty("import_as_name1")).getValue();
  }
}
