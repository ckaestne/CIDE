package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class whereDecls extends GenASTNode {
  public whereDecls(decls decls, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<decls>("decls", decls)
    }, firstToken, lastToken);
  }
  public whereDecls(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new whereDecls(cloneProperties(),firstToken,lastToken);
  }
  public decls getDecls() {
    return ((PropertyOne<decls>)getProperty("decls")).getValue();
  }
}
