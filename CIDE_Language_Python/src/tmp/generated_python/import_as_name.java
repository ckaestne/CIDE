package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class import_as_name extends GenASTNode {
  public import_as_name(AnyName anyName, Name name, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AnyName>("anyName", anyName),
      new PropertyZeroOrOne<Name>("name", name)
    }, firstToken, lastToken);
  }
  public import_as_name(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new import_as_name(cloneProperties(),firstToken,lastToken);
  }
  public AnyName getAnyName() {
    return ((PropertyOne<AnyName>)getProperty("anyName")).getValue();
  }
  public Name getName() {
    return ((PropertyZeroOrOne<Name>)getProperty("name")).getValue();
  }
}
