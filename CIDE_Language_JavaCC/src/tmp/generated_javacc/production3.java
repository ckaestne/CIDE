package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class production3 extends production {
  public production3(token_manager_decls token_manager_decls, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<token_manager_decls>("token_manager_decls", token_manager_decls)
    }, firstToken, lastToken);
  }
  public production3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new production3(cloneProperties(),firstToken,lastToken);
  }
  public token_manager_decls getToken_manager_decls() {
    return ((PropertyOne<token_manager_decls>)getProperty("token_manager_decls")).getValue();
  }
}
