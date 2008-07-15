package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class class_declaration extends GenASTNode {
  public class_declaration(identifier identifier, class_base class_base, class_body class_body, ASTTextNode text220, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<identifier>("identifier", identifier),
      new PropertyZeroOrOne<class_base>("class_base", class_base),
      new PropertyOne<class_body>("class_body", class_body),
      new PropertyZeroOrOne<ASTTextNode>("text220", text220)
    }, firstToken, lastToken);
  }
  public class_declaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new class_declaration(cloneProperties(),firstToken,lastToken);
  }
  public identifier getIdentifier() {
    return ((PropertyOne<identifier>)getProperty("identifier")).getValue();
  }
  public class_base getClass_base() {
    return ((PropertyZeroOrOne<class_base>)getProperty("class_base")).getValue();
  }
  public class_body getClass_body() {
    return ((PropertyOne<class_body>)getProperty("class_body")).getValue();
  }
  public ASTTextNode getText220() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text220")).getValue();
  }
}
