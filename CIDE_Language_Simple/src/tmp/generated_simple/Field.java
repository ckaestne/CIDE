package tmp.generated_simple;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Field extends GenASTNode {
  public Field(Name name, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Name>("name", name)
    }, firstToken, lastToken);
  }
  public Field(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Field(cloneProperties(),firstToken,lastToken);
  }
  public Name getName() {
    return ((PropertyOne<Name>)getProperty("name")).getValue();
  }
}
