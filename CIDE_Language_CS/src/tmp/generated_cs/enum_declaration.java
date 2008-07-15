package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class enum_declaration extends GenASTNode {
  public enum_declaration(identifier identifier, enum_base enum_base, enum_body enum_body, ASTTextNode text272, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<identifier>("identifier", identifier),
      new PropertyZeroOrOne<enum_base>("enum_base", enum_base),
      new PropertyOne<enum_body>("enum_body", enum_body),
      new PropertyZeroOrOne<ASTTextNode>("text272", text272)
    }, firstToken, lastToken);
  }
  public enum_declaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new enum_declaration(cloneProperties(),firstToken,lastToken);
  }
  public identifier getIdentifier() {
    return ((PropertyOne<identifier>)getProperty("identifier")).getValue();
  }
  public enum_base getEnum_base() {
    return ((PropertyZeroOrOne<enum_base>)getProperty("enum_base")).getValue();
  }
  public enum_body getEnum_body() {
    return ((PropertyOne<enum_body>)getProperty("enum_body")).getValue();
  }
  public ASTTextNode getText272() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text272")).getValue();
  }
}
