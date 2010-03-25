#include <stdio.h>
#include <stdlib.h>

#define NOSORT 0
#define INSERTIONSORT 1
#define BUBBLESORT 2

#define DLINKED 1
#define SORTALGO INSERTIONSORT
#if SORTALGO != NOSORT
#define SORTORDER 1
// SORTORDER == 0   -> upward
// SORTORDER == 1   -> downward
#endif

typedef struct T_node {
	int item;
	struct T_node *next;
#if DLINKED
	struct T_node *prev;
#endif
} node;

node* first = NULL;
#if DLINKED
node* last = NULL;
#endif

node* initNode(int item);
void insert(node* elem);
void iterate();
void deleteAll();

node* initNode(int item) {
	node* new;
	new = (node*)malloc(sizeof(node));
	new->item = item;
	new->next = NULL;
#if DLINKED
	new->prev = NULL;
#endif
}

void insert(node* elem) {
#if SORTALGO == BUBBLESORT || SORTALGO == INSERTIONSORT
	node *a = NULL;
	node *b = NULL;
#endif
#if SORTALGO == BUBBLESORT
	node *c = NULL;
	node *e = NULL;
	node *tmp = NULL;
#endif

	if (NULL == first) {
		first = elem;
	} else {
#if SORTALGO == INSERTIONSORT
		a = first;
		b = first->next;

		if (first->item
#if SORTORDER == 0
			>
#else
			<
#endif
			elem->item) {
#endif
			elem->next = first;
#if DLINKED
			first->prev = elem;
#endif
			first = elem;
#if SORTALGO == INSERTIONSORT
			return;
		}
		while (NULL != b && b->item
#if SORTORDER == 0
			<
#else
			>
#endif
			elem->item) {
			a = a->next;
			b = b->next;
		}
		a->next = elem;
		elem->next = b;
#if DLINKED
		elem->prev = a;
		if (NULL != b)
			b->prev = elem;
#endif
#endif
	}

#if SORTALGO == BUBBLESORT
	while(e != first->next) {
		c = a = first;
		b = a->next;
		while(a != e) {
			if(a->item
#if SORTORDER == 0
				>
#else
				<
#endif
				b->item) {
				if (a == first) {
					tmp = b -> next;
					b->next = a;
					a->next = tmp;
#if DLINKED
					a->prev = b;
#endif
					first = b;
					c = b;
				} else {
					tmp = b->next;
					b->next = a;
					a->next = tmp;
					c->next = b;
#if DLINKED
					b->prev = c;
					a->prev = b;
#endif
					c = b;
				}
			} else {
				c = a;
				a = a->next;
			}
			b = a->next;
			if (b == e)
				e = a;
		}
	}
#endif
}

void iterate() {
	node* cur = first;
	unsigned char i = 1;

	while (NULL != cur) {
#if DLINKED
		printf("%2d. prev %10p [%10p : %2d] next %10p\n", i++, cur->prev, cur, cur->item, cur->next);
#else
		printf("%2d. [%10p : %2d] next %10p\n", i++, cur, cur->item, cur->next);
#endif
		cur = cur->next;
	}
}

void deleteAll() {
	node* one;
	node* two;

	if (NULL == first)
		return;

	one = first;
	two = first->next;

	while (NULL != two) {
		one = two;
		two = two->next;
		one->next = NULL;
#if DLINKED
		if (two != NULL)
			two->prev = NULL;
#endif
		free(one);
	}
}

int main(void) {
	int test[] = {8, 3, 2, 6, 1, 5, 4, 7, 9, 0};
	int i = 0;

	/* insert some numbers into the linked list */
	for(i = 0; i < 10; i++) {
		insert(initNode(test[i]));
	}
	iterate();
	deleteAll();

	return 0;
}
