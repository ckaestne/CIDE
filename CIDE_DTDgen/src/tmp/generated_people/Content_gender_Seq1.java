package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_gender_Seq1 extends GenASTNode {
  public Content_gender_Seq1(ASTStringNode pcdata, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("pcdata", pcdata)
    }, firstToken, lastToken);
  }
  public Content_gender_Seq1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_gender_Seq1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getPcdata() {
    return ((PropertyOne<ASTStringNode>)getProperty("pcdata")).getValue();
  }
}
