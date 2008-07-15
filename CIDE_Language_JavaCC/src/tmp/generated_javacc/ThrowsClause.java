package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ThrowsClause extends GenASTNode {
  public ThrowsClause(ArrayList<Name> name, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<Name>("name", name)
    }, firstToken, lastToken);
  }
  public ThrowsClause(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ThrowsClause(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Name> getName() {
    return ((PropertyList<Name>)getProperty("name")).getValue();
  }
}
