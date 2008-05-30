package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content_head_Seq1 extends GenASTNode {
  public Content_head_Seq1(ArrayList<Content_head_Choice1> content_head_Choice1, Content_head_Choice2 content_head_Choice2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<Content_head_Choice1>("content_head_Choice1", content_head_Choice1),
      new PropertyOne<Content_head_Choice2>("content_head_Choice2", content_head_Choice2)
    }, firstToken, lastToken);
  }
  public Content_head_Seq1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Content_head_Seq1(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Content_head_Choice1> getContent_head_Choice1() {
    return ((PropertyZeroOrMore<Content_head_Choice1>)getProperty("content_head_Choice1")).getValue();
  }
  public Content_head_Choice2 getContent_head_Choice2() {
    return ((PropertyOne<Content_head_Choice2>)getProperty("content_head_Choice2")).getValue();
  }
}
