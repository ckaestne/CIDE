package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Import extends GenASTNode {
  public Import(dotted_as_name dotted_as_name, ArrayList<dotted_as_name> dotted_as_name1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<dotted_as_name>("dotted_as_name", dotted_as_name),
      new PropertyZeroOrMore<dotted_as_name>("dotted_as_name1", dotted_as_name1)
    }, firstToken, lastToken);
  }
  public Import(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Import(cloneProperties(),firstToken,lastToken);
  }
  public dotted_as_name getDotted_as_name() {
    return ((PropertyOne<dotted_as_name>)getProperty("dotted_as_name")).getValue();
  }
  public ArrayList<dotted_as_name> getDotted_as_name1() {
    return ((PropertyZeroOrMore<dotted_as_name>)getProperty("dotted_as_name1")).getValue();
  }
}
