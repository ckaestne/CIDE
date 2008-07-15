package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class token_manager_decls extends GenASTNode {
  public token_manager_decls(ClassOrInterfaceBody classOrInterfaceBody, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ClassOrInterfaceBody>("classOrInterfaceBody", classOrInterfaceBody)
    }, firstToken, lastToken);
  }
  public token_manager_decls(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new token_manager_decls(cloneProperties(),firstToken,lastToken);
  }
  public ClassOrInterfaceBody getClassOrInterfaceBody() {
    return ((PropertyOne<ClassOrInterfaceBody>)getProperty("classOrInterfaceBody")).getValue();
  }
}
