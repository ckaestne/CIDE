package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PackageDeclaration extends GenASTNode {
  public PackageDeclaration(Name name, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Name>("name", name)
    }, firstToken, lastToken);
  }
  public PackageDeclaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PackageDeclaration(cloneProperties(),firstToken,lastToken);
  }
  public Name getName() {
    return ((PropertyOne<Name>)getProperty("name")).getValue();
  }
}
