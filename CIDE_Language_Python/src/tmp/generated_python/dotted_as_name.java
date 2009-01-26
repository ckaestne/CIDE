package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class dotted_as_name extends GenASTNode {
  public dotted_as_name(dotted_name dotted_name, Name name, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<dotted_name>("dotted_name", dotted_name),
      new PropertyZeroOrOne<Name>("name", name)
    }, firstToken, lastToken);
  }
  public dotted_as_name(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new dotted_as_name(cloneProperties(),firstToken,lastToken);
  }
  public dotted_name getDotted_name() {
    return ((PropertyOne<dotted_name>)getProperty("dotted_name")).getValue();
  }
  public Name getName() {
    return ((PropertyZeroOrOne<Name>)getProperty("name")).getValue();
  }
}
