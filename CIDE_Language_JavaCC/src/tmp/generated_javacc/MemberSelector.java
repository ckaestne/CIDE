package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MemberSelector extends GenASTNode {
  public MemberSelector(TypeArguments typeArguments, JavaIdentifier javaIdentifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<TypeArguments>("typeArguments", typeArguments),
      new PropertyOne<JavaIdentifier>("javaIdentifier", javaIdentifier)
    }, firstToken, lastToken);
  }
  public MemberSelector(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MemberSelector(cloneProperties(),firstToken,lastToken);
  }
  public TypeArguments getTypeArguments() {
    return ((PropertyOne<TypeArguments>)getProperty("typeArguments")).getValue();
  }
  public JavaIdentifier getJavaIdentifier() {
    return ((PropertyOne<JavaIdentifier>)getProperty("javaIdentifier")).getValue();
  }
}
